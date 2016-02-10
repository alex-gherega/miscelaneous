(ns koch.rules {:author "avr.Phd. alex gherega; https://github.com/alex-gherega"}
    (:require [koch.utils :as kils]))

(defn- turn [direction angle]
  (direction angle))

(defn turn-left [angle]
  (turn - angle))

(defn turn-right [angle]
  (turn + angle))

(defmacro basic-koch-it [k-expr angle]
  ;;TODO: change the [] to a propper s-expr lie `()'
  `(list ~k-expr
         (turn-left ~angle)
         ~k-expr
         (turn-right ~angle)
         (turn-right ~angle)
         ~k-expr
         (turn-left ~angle)
         ~k-expr))
