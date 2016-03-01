(ns longest-collatz-sequence.core
  (:gen-class))

(defn get-next-term [term]
  (if (even? term)
    (/ term 2)
    (+ (* 3 term) 1)))

(defn get-chain-length
  ([term]
   (if (= term 1)
     1
     (get-chain-length (get-next-term term) 2)))
  ([term current-chain-length]
   (if (= term 1)
     current-chain-length
     (get-chain-length (get-next-term term) (+ current-chain-length 1)))))

(defn -main
  [& args]
  (let [chain-lengths (map get-chain-length (range 2 1000000 1))]
    (println (+ (.indexOf chain-lengths (apply max chain-lengths)) 2))))
