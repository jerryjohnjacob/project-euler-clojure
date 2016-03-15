(ns power-digit-sum.core
  (:gen-class))

(require '[clojure.math.numeric-tower :as num-tower])
(require '[clojure.string :as str])

(defn parse-bigint [s]
  (bigint (re-find  #"\d+" s)))

(defn sum-of-numbers [numbers]
  (reduce + (map parse-bigint numbers)))

(defn sum-of-digits [number]
  (sum-of-numbers (str/split (.toString number) #"\D*")))

(defn power [number exponent]
  (num-tower/expt number exponent))

(defn -main
  [& args]
  (println (sum-of-digits (power 2 1000))))
