(ns gipf-online.engine.gipf
  (:use [gipf-online.engine.gipf-core :only [create-empty-board create-space valid-move? alternate-player direction place-piece slide-piece]]))

(defn create-board[]
  "Create an empty gipf board"
  (create-empty-board))

(defn move
  "Move piece onto board (in current-player colour). If move invalid, nothing happens."
  [board source dest]
  (let [colour (:current-player board)
        dir (direction source dest) ]
  (if (valid-move? source dest)
    (alternate-player (slide-piece (place-piece board source colour) source dir))
    board)))

(defn create-basic-board []
  "Create a board with corners pre-populated of basic game"
  (place-piece
    (place-piece
      (place-piece
        (place-piece
          (place-piece
            (place-piece (create-empty-board) 
              :B2 :white)
            :B5 :black)
          :E8 :white)
        :H5 :black)
      :H2 :white)
    :E2 :black))
