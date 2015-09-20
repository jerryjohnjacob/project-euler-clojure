(ns sum-square-difference.core
  (:gen-class))

(defn square [x]
  (* x x)
  )

(defn -main
  [& args]
  (def number 100)
  (println (- (square (reduce + (range 1 (+ 1 number))))
              (reduce + (map square (range 1 (+ 1 number)))))))
