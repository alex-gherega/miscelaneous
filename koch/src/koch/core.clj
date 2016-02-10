(ns koch.core {:author "avr.Phd. alex gherega; https://github.com/alex-gherega"}
  (:use clojure.core
        clojure.repl)
  (:require [koch.rules :as kules]))

(defn do-koch
  ([n]
   (do-koch n
            #(kules/basic-koch-it % 60)))

  ([n rule]
   (do-koch n :F rule))

  ([n forward rule]
   (loop [level n
          kls forward]
     (if (-> level zero?)
       kls
       (recur (dec level)
              (rule kls))))))
