(ns gipf-online.engine.gipf
  (:use [gipf-online.engine.gipf-core :only [create-column]]))

(defn create-board 
  "Create an empty board"
  [width height]
  (reduce conj {} 
    (map 
      #(create-column % width height) 
      (range 1 (+ 1 width)))))
