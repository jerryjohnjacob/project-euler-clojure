(ns sum-of-even-fibonacci-terms.core
  (:gen-class))

(def limit 4000000)

(defn sum-of-array [array]
  (reduce + array)
)

(defn generate-fibonacci-series [first-term second-term]
  (into [first-term]
        (if (> (+ first-term second-term) limit)
          [second-term]
          (generate-fibonacci-series second-term (+ first-term second-term))
          )
        )
  )

(defn -main
  [& args]
  (println (sum-of-array (filter even? (generate-fibonacci-series 0 1))))
  )
