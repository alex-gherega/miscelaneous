(ns packesel.koch.utils {}
    (:require [packesel.config :as pakonf]
              [koch.config :as konf]
              [packesel.config :as pakonf]))

(defn scale
  ([seglen]
   (/ seglen (:scale (pakonf/read-conf))))

  ([seglen factor]
   (/ seglen factor)))


(defn koch-to-svg [koch-ds line-fn]

  (reduce #(into %1 (if (= %2 :L)
                      (line-fn)
                      (do (konf/update-cuda %2) nil)))
       [] koch-ds))
