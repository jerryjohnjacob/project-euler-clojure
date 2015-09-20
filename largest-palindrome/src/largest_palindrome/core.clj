(ns largest-palindrome.core
  (:gen-class))

(defn first-half [string]
  (let [string-midpoint (/ (count string) 2)]
    (subs string 0 string-midpoint))
  )

(defn second-half [string]
  (let [string-length (count string)]
        (let [string-midpoint (+ (/ string-length 2)
                                 (if (even? string-length)
                                   0
                                   1
                                   )
                                 )
              ]
          (subs string string-midpoint string-length))
        )
  )

(defn is-palindrome? [number]
  (let [stringified-number (str number)]
    (= (first-half stringified-number)
       (clojure.string/reverse (second-half stringified-number))
       )
    )
  )

(defn pairs-of-compatibles [max-limit]
  (let [z (range max-limit -1 -1)]
    (for [x z y z :when (is-palindrome? (* x y))]
      (* x y))
    )
  )

(defn -main
  [& args]
  (println (apply max(pairs-of-compatibles 999)))
  )
