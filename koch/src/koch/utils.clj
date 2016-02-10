(ns koch.utils)

(defn to-rads [angle]
  "Convert an angle from degrees to radians"
  (/ (* angle Math/PI)
     180.0))

(defn end-point [x y angle length]
  "Compute the end point of a line segment given the
starting point, the length of the segment and the direction"
  (let [trans-fn #(+ %1 (* %2 (%3 %4)))]

    [(trans-fn x length  #(Math/sin %) angle)
     (trans-fn y length  #(Math/cos %) angle)]))
