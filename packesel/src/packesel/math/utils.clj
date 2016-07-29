(ns packesel.math.utils)

(defn abs [x]
  (and x (if (pos? x)
           x
           (- x))))
