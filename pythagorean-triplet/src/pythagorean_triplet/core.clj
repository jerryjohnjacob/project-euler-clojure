(ns pythagorean-triplet.core
  (:gen-class))

(require 'clojure.set)

(defn square [n]
  (* n n))

(defn get-a [p, q]
  (* 2 p q))

(defn get-b [p, q]
  (- (square p) (square q)))

(defn get-c [p, q]
  (+ (square p) (square q)))

(defn is-factor? [main-number number-to-check]
  (== 0 (rem main-number number-to-check)))

(defn round-down [n]
  (Math/floor n))

(defn list-of-factors [n]
  (for [x (range 1 (+ (round-down (/ n 2)) 1))
        :let [y x]
        :when (is-factor? n y)]
    y))

(defn have-no-common-divisors? [p, q]
  (= (count (clojure.set/intersection (set (list-of-factors p))
                                      (set (list-of-factors q))))
     1))

(defn -main
  [& args]
  (loop [p 2 q 2]
    (if (and (have-no-common-divisors? p q)
             (> p q))
      (do
        (let [a (get-a p q) b (get-b p q) c (get-c p q)]
          (let [sumabc (+ a b c)]
            (if (= 1000 sumabc)
              (println (* a b c))
              (do
                (if (< sumabc 1000)
                  (do
                    (recur (inc p) q))
                  (do
                    (recur 2 (inc q)))))))))
      (recur (inc p) q))))
