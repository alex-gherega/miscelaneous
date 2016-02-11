(ns koch.core {:author "avr.Phd. alex gherega; https://github.com/alex-gherega"}
  (:use clojure.core
        clojure.repl)
  (:require [koch.rules :as kules]
            [koch.config :as konf]))

(defn do-koch-line
  ([n]
   (do-koch-line n
                 #(kules/basic-koch-it %
                                       @konf/tura)))

  ([n rule]
   (do-koch-line n :F rule))

  ([n forward rule]
   (loop [level n
          kls forward]
     (if (-> level zero?)
       kls
       (recur (dec level)
              (rule kls))))))

(defn do-koch
  ([n] (do-koch n do-koch-line))

  ([n koch-line-fn] (kules/axiom (koch-line-fn n)
                    @konf/tura)))
