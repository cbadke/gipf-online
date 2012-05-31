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

(fact "horizontal movements calc row numbers correctly"
  (up-right :B3) => :C4
  (up-right :E5) => :F5
  (down-right :C3) => :D3
  (down-right :E5) => :F4
  (up-left :D6) => :C6
  (up-left :G2) => :F3
  (down-left :C6) => :B5
  (down-left :H6) => :G6)

(fact "vertical movements only change row"
  (up :B3) => :B4
  (down :E5) => :E4)

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
        new-spaces {:B2 {:colour :white}, :C2 {:colour :black}, :D2 {:colour :white}}
        board (assoc e-board :spaces (conj (:spaces e-board) new-spaces))]
    (:B2 (:spaces (slide-piece board :B2 up-right))) => {:colour :empty}
    (:C2 (:spaces (slide-piece board :B2 up-right))) => {:colour :white}
    (:D2 (:spaces (slide-piece board :B2 up-right))) => {:colour :black}
    (:E2 (:spaces (slide-piece board :B2 up-right))) => {:colour :white}))
