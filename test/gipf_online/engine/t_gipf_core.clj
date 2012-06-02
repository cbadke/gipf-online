(ns gipf-online.engine.t-gipf-core
  (:use [gipf-online.engine.gipf-core])
  (:use [midje.sweet]))

(fact "can create board columns"
  (sort (keys (create-column 1))) => '(:A1 :A2 :A3 :A4 :A5)
  (sort (keys (create-column 2))) => '(:B1 :B2 :B3 :B4 :B5 :B6)
  (sort (keys (create-column 3))) => '(:C1 :C2 :C3 :C4 :C5 :C6 :C7)
  (sort (keys (create-column 9))) => '(:I1 :I2 :I3 :I4 :I5))

(fact "spaces take get coordinate"
  (keys (create-space :A1 :empty)) => '(:A1))

(fact "spaces take colour"
  (((create-space :A1 :black) :A1) :colour) => :black)

(fact "edge positions can see inner positions"
  (up-right :A1) => :B2
  (down-right :A2) => :B2
  (up :B1) => :B2
  (down :B6) => :B5
  (up-left :I1) => :H2
  (down-left :H6) => :G6)

(fact "inner positions cannot see edge positions"
  (up-right :H2) => nil
  (down-right :E2) => nil
  (up :B5) => nil
  (down :H2) => nil
  (up-left :B5) => nil
  (down-left :D2) => nil)

(fact "move from left cell calcs correctly"
  (up :C4) => :C5
  (up-right :C4) => :D5
  (up-left :C4) => :B4
  (down :C4) => :C3
  (down-right :C4) => :D4
  (down-left :C4) => :B3)

(fact "move from right cell calcs correctly"
  (up :G4) => :G5
  (up-right :G4) => :H4
  (up-left :G4) => :F5
  (down :G4) => :G3
  (down-right :G4) => :H3
  (down-left :G4) => :F4)

(fact "move from center calcs correctly"
  (up :E5) => :E6
  (up-right :E5) => :F5
  (up-left :E5) => :D5
  (down :E5) => :E4
  (down-right :E5) => :F4
  (down-left :E5) => :D4)

(fact "Direction returns direction function from source to dest"
  ((direction :A1 :B2) :A1) => :B2
  ((direction :A2 :B2) :A2) => :B2
  ((direction :B2 :B3) :B2) => :B3
  ((direction :B3 :B2) :B3) => :B2
  ((direction :F3 :E3) :F3) => :E3
  ((direction :F3 :E4) :F3) => :E4
  (direction :B5 :A4) => nil)

(fact "moving from space other than the edge is not valid"
  (valid-move? :B3 :B4) => false)

(fact "moving from edge to non-adjacent space is not valid"
  (valid-move? :A1 :E4) => false)

(fact "moving from edge to edge is not valid"
  (valid-move? :A1 :A2) => false)

(fact "moving from edge to adjacent non-edge is valid"
  (valid-move? :A1 :B2) => true)

(fact "sliding into full row is not valid"
  (let [e-board (create-empty-board)
        new-spaces {:B2 {:colour :white}, :C2 {:colour :white}, :D2 {:colour :white}, :E2 {:colour :white}}
        board (assoc e-board :spaces (conj (:spaces e-board) new-spaces))]
    (valid-slide? board :A1 up-right) => true
    (valid-slide? board :A2 down-right) => false))

(fact "can create board"
  (count ((create-empty-board) :spaces)) => 61)

(fact "board is empty"
  (every? #(= :empty (:colour (val %))) ((create-empty-board) :spaces)) => true)

(fact "white starts"
  (:current-player (create-empty-board)) => :white)

(fact "alternate-player toggles player"
  (:current-player (alternate-player (create-empty-board))) => :black
  (:current-player (alternate-player (alternate-player (create-empty-board)))) => :white)

(fact "slide-piece bumps pieces in"
  (let [e-board (create-empty-board)
        board (place-piece e-board :B2 :white)]
    (:B2 (:spaces (slide-piece board :B2 down-right))) => {:colour :empty}
    (:C2 (:spaces (slide-piece board :B2 down-right))) => {:colour :white}))

(fact "slide-piece bumps pieces in"
  (let [e-board (create-empty-board)
        board (place-piece
                (place-piece
                  (place-piece e-board
                    :B2 :white)
                  :C2 :black)
                :D2 :white)]
    (:B2 (:spaces (slide-piece board :B2 down-right))) => {:colour :empty}
    (:C2 (:spaces (slide-piece board :B2 down-right))) => {:colour :white}
    (:D2 (:spaces (slide-piece board :B2 down-right))) => {:colour :black}
    (:E2 (:spaces (slide-piece board :B2 down-right))) => {:colour :white}))
