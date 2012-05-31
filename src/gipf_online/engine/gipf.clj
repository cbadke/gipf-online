(ns gipf-online.engine.gipf
  (:use [gipf-online.engine.gipf-core :only [create-empty-board create-space valid-move? alternate-player direction slide-piece]]))

(defn create-board[]
  "Create an empty gipf board"
  (create-empty-board))

(defn move
  "Move piece onto board (in current-player colour). If move invalid, nothing happens."
  [board source dest]
  (if (valid-move? source dest)
    (alternate-player
      (slide-piece board source (direction source dest)))
    board))

(defn create-basic-board []
  "Create a board with corners pre-populated of basic game"
  (move
    (move
      (move
        (move
          (move
            (move (create-board) 
                  :A1 :B2)
            :A5 :B5)
          :E9 :E8)
        :I5 :H5)
      :H1 :H2)
    :E1 :E2))
