(ns largest-product-in-a-grid.core
  (:gen-class))
(use 'clojure.java.io)
(require 'clojure.string)

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

(defn get-sequence [line]
  (into [] (map parse-int (clojure.string/split line #" "))))

(defn get-grid []
  (with-open [rdr (reader (str (System/getProperty "user.dir") "/resources/grid.txt"))]
    (into [] (doall (map get-sequence (line-seq rdr))))))

(def grid (get-grid))

(def max-limit (count grid))

(defn get-element [x y]
  (get (get grid y) x))

(defn get-four-elements-to-bottom [x y]
  (if (> (+ x 3) (dec max-limit))
    []
    (for [i (range 0 4)]
      (get-element (+ x i) y)
      )))
  
(defn get-four-elements-to-right [x y]
  (if (> (+ y 3) (dec max-limit))
    []
    (for [i (range 0 4)]
      (get-element x (+ y i))
      )))

(defn get-four-elements-to-bottom-right [x y]
  (if (or (> (+ x 3) (dec max-limit)) (> (+ y 3) (dec max-limit)))
    []
    (for [i (range 0 4)]
      (get-element (+ x i) (+ y i))
      )))

(defn get-four-elements-to-top-left [x y]
  (if (or (< (- x 3) 0) (> (+ y 3) (dec max-limit)))
    []
    (for [i (range 0 4)]
      (get-element (- x i) (+ y i))
      )))

(defn get-elements [x y]
  [
   (get-four-elements-to-right x y)
   (get-four-elements-to-bottom x y)
   (get-four-elements-to-bottom-right x y)
   (get-four-elements-to-top-left x y)
   ])

(defn get-products-of-quadruplets [big-list]
  (for [[a b c d] (partition 4 (flatten big-list))](* a b c d)))

(defn -main
  [& args]
  (println (apply max (get-products-of-quadruplets (for [i (range 0 max-limit)]
                                                     (for [j (range 0 max-limit)]
                                                       (get-elements i j)))))))
