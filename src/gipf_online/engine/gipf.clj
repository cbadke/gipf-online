(ns gipf-online.engine.gipf
  (:use [gipf-online.engine.gipf-core :only [create-column create-space valid-move?]]))

(defn create-empty-board []
  "Create an empty gipf board"
  { :spaces (reduce conj {} (map 
                             #(create-column %) 
                             (range 1 10)))
    :current-player :white })

(defn move
  "Move piece onto board (in current-player colour). If move invalid, nothing happens."
  [board source dest]
  (if (valid-move? source dest)
    (assoc board 
           :spaces 
           (conj 
             (board :spaces) 
             (create-space dest (board :current-player))))
    board))
