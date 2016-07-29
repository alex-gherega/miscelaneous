(ns largeno.core
  (:require [stats.hist :as stist]
            [largeno.generation :as genion]))

(defn foo
  "I don't do a whole lot."
  [x])

(defn hist-large-rands []
  (let [xs (repeatedly 700 (partial genion/gen-large-int 512))]
    (stist/draw-asci-hist
     (* 1 (.pow (BigInteger. "10") 511))
     (dec (.pow (BigInteger. "10") 512))
     70
     xs
     13)))
