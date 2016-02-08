(ns koch.core
  (:require [koch.rules :as kules]))

(defn do-koch [level]
  (loop [level level
         kls :F]
    (if (-> level zero?)
      kls
      (recur (dec level)
             (kules/koch-it kls 60)))))
