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
         (>= column (calc-column-height column)))))

(defn up-right
  [coord]
  (let [[column row] (parse-coord coord)
        new-column (+ 1 column)
        new-row (if
                  (< column 5)
                  (+ 1 row)
                  row)]
    (if 
      (valid-destination? new-column new-row)
      (build-coord new-column new-row)
      nil))) 

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


