(ns engine.t-core
  (:use [engine.core])
  (:use [midje.sweet]))

(fact "can create hex board"
  (count (hex-board 3 3)) => 7
  (count (hex-board 9 9)) => 61)

(fact "can create hex board columns"
  (make-hex-column 1 4 4) => '(:A1 :A2 :A3)
  (make-hex-column 2 4 4) => '(:B1 :B2 :B3 :B4)
  (make-hex-column 3 4 4) => '(:C1 :C2 :C3 :C4)
  (make-hex-column 4 4 4) => '(:D1 :D2 :D3))
