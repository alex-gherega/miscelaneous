(ns largeno.generation)

(defn gen-large-int [n]
  (loop [n n r '()]
    (if (zero? n)
      r
      (recur (dec n) (conj r (rand-int 10))))))
