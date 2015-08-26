(ns multiples-of-3-and-5.core
  (:gen-class))

(defn is-multiple? [number-to-check number]
  (== 0 (rem number-to-check number))
)

(defn sum-of-array [array]
  (reduce + array)
)

(defn get-multiples [number current-iteration current-list]
  (if (< current-iteration number)
    current-list
    (if (is-multiple? current-iteration number)
      (get-multiples number
                     (- current-iteration 1)
                     (into current-list [current-iteration]))
      (get-multiples number
                     (- current-iteration 1)
                     current-list
                     )
      )
    )
  )

(defn -main
  [& args]
  (println (sum-of-array (distinct (into (get-multiples 3 1000 [])
                                         (get-multiples 5 1000 [])))))
)
