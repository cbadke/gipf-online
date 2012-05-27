(ns gipf-online.engine.gipf
  (:use [gipf-online.engine.gipf-core :only [create-column]]))

(defn create-empty-board []
  "Create an empty gipf board"
  (reduce conj {} 
    (map #(create-column %) (range 1 10))))
