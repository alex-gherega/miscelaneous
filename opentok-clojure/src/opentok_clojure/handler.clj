(ns opentok-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [clojure.java.io :as io]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clj-http.client :as client]
            [org.httpkit.client :as http]
            [clojure.data.json :as json]))

(defroutes app-routes
  (GET "/" [] (let [resp ""])
       ;(http/get "https://apprtc.appspot.com/r/658760776")
       (Thread/sleep 4000)
       "Hello")
  (GET "/main" []
       {:status 200
        :headers {"Content-Type" "text/html; charset=utf-8"}
        :body (slurp "resources/public/main.html")}
       ;(io/resource "public/main.html")
       )
  ;;PIE CARE DEBUG MOCK
  (GET "/nanny/register" [phone]
       (println "REGISTERING..." phone)
       (json/write-str {:id "alex"
                        :phoneNumber "0742070285"
                        :channelId ""})
       )

  (GET "/nanny/collectcontacts" [phone]
       (println "COLLECTING..." phone)
       (json/write-str {:contacts [{:id "alex"
                                    :phoneNumber "0746315293"
                                    :channelId ""}]})
       )

  (GET "/nanny/identifychance" [contacts]
       (println "IDENTIFYING..." contacts)
       (json/write-str {:channels ["54321"]})
       )

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
