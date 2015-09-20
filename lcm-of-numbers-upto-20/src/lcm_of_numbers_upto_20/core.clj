(ns lcm-of-numbers-upto-20.core
  (:gen-class))

(defn divisible? [num1, num2]
  (= (rem num1 num2) 0)
  )

(defn check-divisibility [range, num]
  (every? identity (map divisible? (for [x range] num) range))
)

(defn divisible-by-all? [num]
  (if (odd? num)
    false
    (let [number (* num 10)]
      (if (check-divisibility [19, 17, 13, 11, 7, 5, 3] number)
        (check-divisibility [18, 16, 15, 14, 12, 10, 9, 8, 6, 4] number)
        false
        )
      )
    )
)

(defn -main
  [& args]
  (def number (reduce * [19, 17, 13, 11, 7, 5, 3, 2, 2]))
  (println (* 10
              (loop [num number]
                (if (divisible-by-all? num)
                  num
                  (recur (inc num))
                  )
                )
              )
           )
  )
