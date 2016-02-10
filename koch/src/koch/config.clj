(ns koch.config {:author "avr. Phd. alex gherega; https://github.com/alex-gherega"
                 :doc "This ns contains the basic config for building& drawing a Koch snowflake structure"})

;; vars area
(def seglen
  "This atom holds the current segment length to draw for the Snowflake"
  (atom 10000.0))
(def cda ;; current direction angle
  "This atom holds the current direction angle"
  (atom 0))

;; vars updating fns
(defn update-seglen
  "Update the seglen atom by dividing its initial value"
  ([]
   (update-seglen 3))
  ([denominator]
   (swap! seglen / denominator)))

(defn update-cda
  "Update the current direction angle"
  [new-angle]
  (swap! cda + new-angle))
