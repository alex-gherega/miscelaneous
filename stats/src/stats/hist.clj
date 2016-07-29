(ns stats.hist)

(defn light-classify
  ([start end n x]
   (loop [slice (/ (- end start) n)
          class1 1 class2 n
          x-dec (- x slice) x-inc (+ x slice)]
     (cond (< x-dec start) class1
           (> x-inc end) class2
           :default (recur slice
                           (+ 1 class1)
                           (- class2 1)
                           (- x-dec slice)
                           (+ x-inc slice))))))


(defn classes [n]
  (vec (repeat n 0)))

(defn histografy [start end n xs]
  (vec (reduce (fn [cls x]

                 (let [cl (dec (light-classify start end n x))
                       _ (println " DBG: " cls x cl)]
                   (assoc cls cl (inc (cls cl)))))
               (classes n)
               xs)))

(defn dec-or-zero [x]
  (cond (>= x 1) (dec x)
        :default 0))

(defn to-percentage [hist scale]
  (let [peak (apply max hist)]
    (vec (map #(* scale (/ % peak)) hist))))

(defn to-2d [hist]
  (loop [hist hist array []]
    (if (every? zero? hist)
      array
      (recur (vec (map #(dec-or-zero %) hist))
             (conj array (vec (map #(if (pos? %) " * " "   ") hist)))))))

(defn draw-asci-hist [hist-2d]
  (vec (reduce #(str %1 (apply str %2) "\n") "" hist-2d)))
