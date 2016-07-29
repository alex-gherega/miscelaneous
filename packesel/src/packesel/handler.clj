(ns packesel.handler
  (:use [analemma.xml :only [emit add-content add-attrs
			     parse-xml transform-xml filter-xml]]
	analemma.svg
	[clojure.java.io :only [file]])

  (:require [packesel.config :as pakonf]
            [packesel.math.utils :as mutils]
            [packesel.koch.svg :as pakog]
            [hiccup.core :as hicore]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn- extract-param [params]
  (if-let [run (:run params)]
    (if (< (read-string run) 6) (read-string run) 5)
    3))

(defroutes app-routes
  (GET "/alex-gherega/packesel/koch" {params :params} []
       (pakog/reset-koch)
       (hicore/html [:body
                     (emit (svg
                            {:width 3000 :height 3000 :x 200 :y 100 :viewPort "30 30 200 200"}
                            (-> (path (into [:M @pakog/s-point] (pakog/do-koch-svg (extract-param params))))
                                (style :stroke "#660000"  :fill :none  :stroke-dasharray "10000"
                                       :stroke-dashoffset "10000" :animation "dash 10s linear forwards")
                                )))
                     [:style {:media "screen" :type "text/css"} "@keyframes dash {to {stroke-dashoffset: 0;}}"]])))

(def app
  (wrap-defaults app-routes site-defaults))
