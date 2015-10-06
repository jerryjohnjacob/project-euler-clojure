(ns large-sum.core
  (:gen-class))
(use 'clojure.java.io)

(defn parse-bigint [s]
  (bigint (re-find  #"\d+" s)))

(defn get-numbers []
  (with-open [rdr (reader (str (System/getProperty "user.dir") "/resources/numbers.txt"))]
    (into [] (doall (line-seq rdr)))))

(defn sum-of-numbers [numbers]
  (reduce + (map parse-bigint numbers)))

(defn get-first-ten-digits [number]
  (subs (str number) 0 10))

(defn -main
  [& args]
  (time (println (get-first-ten-digits (sum-of-numbers (get-numbers))))))
