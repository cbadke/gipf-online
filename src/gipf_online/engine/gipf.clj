(ns gipf-online.engine.gipf
  (:use [gipf-online.engine.gipf-core :only [create-empty-board create-space valid-move?]]))

(defn create-board[]
  "Create an empty gipf board"
  (create-empty-board))

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
