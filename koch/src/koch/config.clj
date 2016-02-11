(ns koch.config {:author "avr. Phd. alex gherega; https://github.com/alex-gherega"
                 :doc "This ns contains the basic config for building& drawing a Koch snowflake structure"})

;; vars area

;; TODO: refactor these varas as part of single atom map holding the configuration
(def seglen
  "This atom holds the current segment length to draw for the Snowflake"
  (atom 10000.0))

(def cuda ;; current direction angle
  "This atom holds the current direction angle"
  (atom 0))

(def tura ;; current turning angle
  "This atom holds the current turning angle"
  (atom 60))

;; vars updating fns
(defn update-seglen
  "Update the seglen atom by dividing its initial value"
  ([]
   (update-seglen 3))
  ([denominator]
   (swap! seglen / denominator)))

(defn update-cuda
  "Update the current direction angle"
  [new-angle]
  (swap! cuda + new-angle))

(defn reset-all []
  (reset! cuda 0)
  (reset! tura 60)
  (reset! seglen 10000))
