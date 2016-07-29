(ns packesel.koch.svg {:author "avr.Phd. alex gherega; https://github.com/alex-gherega"}
    (:use [analemma.xml :only [emit add-content add-attrs
                               parse-xml transform-xml filter-xml]]
          analemma.svg
          [koch.rules :as kules]
          [koch.utils :as kils]
          [koch.core :as kore]
          [koch.config :as konf]
          [packesel.config :as pakonf]
          [packesel.koch.utils :as pakils]))

(konf/reset-all)

(defn- make-start []
  ((juxt :sx :sy) (pakonf/read-conf)))

(def s-point (atom (make-start))) ;; start point to draw

(defn- update-spt [[x y :as new-p]]
  (reset! s-point new-p))

(defn reset-koch []
  (konf/reset-all)
  (pakonf/reset-conf)
  (update-spt (make-start)))

(defn draw-line

  ([]
   (draw-line @konf/cuda))

  ([angle]
   (let [seglen (pakils/scale @konf/seglen)
         ;; e-point (kils/end-point @s-point
         ;;                         (kils/to-rads angle)
         ;;                         seglen)
         ]
     ;(update-spt e-point)
     ;[:L e-point]
     (draw-line seglen angle)))

  ([len angle]
   (draw-line @s-point
              len
              angle))

  ([start len angle]
   (let [[sx sy] start
         [ex ey :as end] (kils/end-point start (kils/to-rads angle) len)]
     [:L end])))

(defn koch-fn [k-expr angle]
  (let [seglen (pakils/scale @konf/seglen 1000)
        e-point (kils/end-point @s-point
                                (kils/to-rads angle)
                                seglen)]
    (update-spt e-point)
    (into k-expr [:L e-point])))


(declare do-koch-svg-fn)

(def do-koch-svg (memoize do-koch-svg-fn))

(defn- do-koch-svg-fn
  ([n]
   (do-koch-svg n (kore/do-koch n
                                #(kore/do-koch-line % :L
                                                    (fn [fwd]
                                                      (kules/vec-koch-it fwd
                                                                         @konf/tura))))))
  ([n koch-ds]
   (let [draw-line (fn [] (let [seglen (pakils/scale @konf/seglen
                                                     (* (Math/pow 3 n) (:scale (pakonf/read-conf))))
                                res (draw-line seglen
                                               @konf/cuda)]
                            (update-spt (second res))
                            res))]
     (pakils/koch-to-svg (flatten koch-ds) draw-line))))

(defn do-koch-svg-line [n]

  (do-koch-svg n (kore/do-koch-line n :L
                                    #(kules/vec-koch-it %
                                                        @konf/tura))))
