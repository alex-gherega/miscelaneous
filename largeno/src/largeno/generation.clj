(ns largeno.generation)

;; TODO: refactor this in several fns
(defn gen-large-int [n]
  (read-string (reduce str
                       ""
                       (take n (loop [n (- n 1)
                                      r `(~(-> 9 rand-int inc))
                                      next-rand (-> 999999999 rand-int str)]
                                 (if (<= n 0)
                                   r
                                   (recur (- n (count next-rand))
                                          (concat r (seq next-rand))
                                          (-> 999999999 rand-int str))))))))
