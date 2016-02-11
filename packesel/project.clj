(defproject packesel "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [org.clojars.pallix/analemma "1.0.0" :exclusions [org.clojure/clojure]]
                 [koch "0.1.0-SNAPSHOT"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler packesel.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
