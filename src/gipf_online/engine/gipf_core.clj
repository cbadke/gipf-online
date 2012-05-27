(ns gipf-online.engine.gipf-core
  (:use [clojure.algo.generic.math-functions :only [abs]]))

(def numbers (iterate inc 1))
(def letters (cycle (seq "ABCDEFGHIJKLMNOPQRSTUVWXYZ")))

(defstruct space :colour)

(defn calc-column-height
  [column width height]
  (let [center-column (if (odd? width) 
                          (/ (+ 1 width) 2) 
                          (if (> column (/ width 2))
                              (+ 1 (/ width 2))
                              (/ width 2)))]
    (- height (abs (- column center-column)))))

(defn create-space 
  "Create a new space at the passed coordinate"
  [coord colour width height]
  (if (= coord :A1)
    {coord (struct space colour)}
    {coord (struct space colour)}))

(defn create-column
  "Create all cells for given column for a board of given width and height."
  [column width height]
  (let [column-height (calc-column-height column width height)
       column-letter (nth letters (- column 1))]
  (reduce conj {} 
          (map #(create-space % :empty width height) 
               (map keyword
                    (map #(str column-letter %)
                         (take column-height numbers)))))))

