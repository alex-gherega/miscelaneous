(ns packesel.config {:author "avr.Phd. alex gherega; https://github.com/alex-gherega"})

(def conf (atom {}))

(defn- updt [m]
  (merge m {:width "2000"
            :height "2000"
            :sx 50 ;; start x
            :sy 100 ;; start y
            :scale 3000
            }))

(defn read-conf []
  @conf)

(defn reset-conf []
  (reset! conf {})
  (swap! conf updt))

(reset-conf)
