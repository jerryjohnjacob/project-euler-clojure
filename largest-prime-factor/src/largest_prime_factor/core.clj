(ns largest-prime-factor.core
  (:gen-class))

(def number 600851475143)

(defn is-factor? [main-number number-to-check]
  (== 0 (rem main-number number-to-check))
)

(defn round-down [n]
  (Math/floor n)
)

(defn list-of-factors [n]
  (for [x (range 1 (+ (round-down (/ n 2)) 1))
        :let [y x]
        :when (is-factor? n y)]
    y)
)

(defn number-of-factors [n]
  (count (list-of-factors n))
  )

(defn is-prime? [n]
  (if (and (even? n) (not= n 2))
    false
    (if (== (number-of-factors (round-down (/ n 2))) 1)
      true
      false
      )
    )
)

(defn is-prime-factor? [main-number number-to-check]
  (and (is-factor? main-number number-to-check) (is-prime? number-to-check))
)

(defn get-prime-factors [n list-of-factors]
  (if (== 1 n)
    list-of-factors
    (get-prime-factors (- n 1)
                       (into list-of-factors (if (is-prime-factor? number n)
                                               [n]
                                               []
                                               )
                             )
                       )
    )
  )

(defn -main
  [& args]
  (println (apply max (get-prime-factors number [])))
  )
