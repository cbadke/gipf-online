(ns engine.core
  (:use [clojure.algo.generic.math-functions :only [abs]]))

(def numbers (iterate inc 1))
(def letters (cycle (seq "ABCDEFGHIJKLMNOPQRSTUVWXYZ")))

(defn create-hex-column
  "Create all cells for given column for a board of given width and height."
  [column width height]
  (let [center-column (if (odd? width) 
                          (/ (+ 1 width) 2) 
                          (if (> column (/ width 2))
                              (+ 1 (/ width 2))
                              (/ width 2)))
        column-height (- height (abs (- column center-column)))
        column-letter (nth letters (- column 1))]
  (map keyword 
       (map (partial str column-letter)
            (take column-height numbers)))))

(defn create-hex-board 
  "Create a fully connected hexagonal board"
  [width height]
  (flatten 
    (map 
      #(create-hex-column % width height) 
      (take width numbers))))
