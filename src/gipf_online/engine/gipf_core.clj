(ns gipf-online.engine.gipf-core
  (:use [clojure.algo.generic.math-functions :only [abs]]))

(def numbers (iterate inc 1))
(def letters (cycle (seq "ABCDEFGHIJKLMNOPQRSTUVWXYZ")))

(defstruct space :colour)

(def board-height 9)
(def board-width 9)
(def board-center 5)

(defn parse-coord
  [coord]
  (let [c-str (seq (name coord))
        col (+ 1 (.indexOf letters (first c-str)))
        row (read-string (str (second c-str)))]
   (list col row)))

(defn build-coord
  [col row]
  (keyword (str (nth letters (- col 1)) row)))

(defn calc-column-height
  [column]
    (- board-height (abs (- column board-center))))

;========movement calculations ================

(defn valid-destination?
  [column row]
  (not (or
         (<= row 1)
         (<= column 1)
         (>= column 9)
         (>= row (calc-column-height column)))))

(defn valid-source?
  [column row]
  (not (valid-destination? column row)))

(defn calc-destination
  [coord hor-func left-vert-func right-vert-func]
  (let [[column row] (parse-coord coord)
        new-column (hor-func column)
        new-row (if
                  (< column 5)
                  (left-vert-func row)
                  (right-vert-func row))]
    (if 
      (valid-destination? new-column new-row)
      (build-coord new-column new-row)
      nil))) 

(defn up
  [coord]
  (calc-destination coord #(+ % 0) #(+ % 1) #(+ % 1)))

(defn up-right
  [coord]
  (calc-destination coord #(+ % 1) #(+ % 1) #(+ % 0)))

(defn up-left
  [coord]
  (calc-destination coord #(- % 1) #(+ % 0) #(+ % 1)))

(defn down
  [coord]
  (calc-destination coord #(+ % 0) #(- % 1) #(- % 1)))

(defn down-right
  [coord]
  (calc-destination coord #(+ % 1) #(- % 0) #(- % 1)))

(defn down-left
  [coord]
  (calc-destination coord #(- % 1) #(- % 1) #(- % 0)))

(defn direction
  "Return the direction function that gets from source to dest. nil if destination is not adjacent to source."
  [source dest]
  (cond
    (= (up source) dest) up
    (= (up-right source) dest) up-right
    (= (up-left source) dest) up-left
    (= (down source) dest) down
    (= (down-right source) dest) down-right
    (= (down-left source) dest) down-left
    :else nil))
;=============================================

(defn create-space 
  "Create a new space at the passed coordinate"
  [coord colour]
    {coord (struct space colour)})

(defn create-column
  "Create all cells for given column for a board of given width and height."
  [column]
  (let [column-height (calc-column-height column)]
    (reduce conj {}
            (map #(create-space % :empty) 
                 (map #(build-coord column %)
                      (take column-height numbers))))))

(defn create-empty-board []
  "Create an empty gipf board"
  { :spaces (reduce conj {} (map 
                             #(create-column %) 
                             (take board-width (iterate inc 1))))
    :current-player :white })

;=============================================

(defn valid-move?
  [source dest]
  (and 
    (apply valid-source? (parse-coord source))
    (apply valid-destination? (parse-coord dest))
    (not (nil? (direction source dest)))))

(defn valid-slide?
  [board source direction]
  (let [dest (direction source)]
    (cond
      (nil? dest) false
      (= :empty (:colour (dest (:spaces board)))) true
      :else (valid-slide? board dest direction))))

(defn slide-piece
  [board source direction]
  (let [dest (direction source)]
  (assoc board 
         :spaces 
         (conj 
           (:spaces board) 
           (create-space 
             (direction source) 
             (board :current-player))))))


(defn alternate-player
  [board]
  (let [new-colour (if 
                     (= :black (:current-player board))
                     :white
                     :black)]
    (assoc board :current-player new-colour)))
