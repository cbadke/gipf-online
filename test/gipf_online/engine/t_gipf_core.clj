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
