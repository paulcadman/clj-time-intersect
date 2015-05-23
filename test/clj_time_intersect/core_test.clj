(ns clj-time-intersect.core-test
  (:require [clojure.test :refer :all]
            [clj-time-intersect.core :refer :all]
            [clj-time.core :as t])
  (:use clj-time.coerce))

(deftest test-intersect-superset
	(testing "intersect"
		(let [start (t/now)]
			(is (t/interval start (t/plus start (t/seconds 15)))
					(intersect (t/interval (t/minus start (t/seconds 10))
															   (t/plus start (t/seconds 20)))
										 (t/interval start 
                                 (t/plus start (t/seconds 15))))
			))))	

(deftest test-intersect-superset-rev
	(testing "intersect"
		(let [start (t/now)]
			(is (t/interval start (t/plus start (t/seconds 15)))
					(intersect (t/interval start 
                                 (t/plus start (t/seconds 15)))
                     (t/interval (t/minus start (t/seconds 10))
															   (t/plus start (t/seconds 20))))
			))))	

(deftest test-intersect-disjoint
	(testing "intersect"
		(let [start (t/now)]
			(is (t/interval start (t/plus start (t/seconds 15)))
					(intersect (t/interval (t/minus start (t/seconds 10))
															   (t/minus start (t/seconds 5)))
										 (t/interval start 
                                 (t/plus start (t/seconds 15))))
			))))

(deftest test-intersect-disjoint-rev
	(testing "intersect"
		(let [start (t/now)]
			(is (t/interval start (t/plus start (t/seconds 15)))
					(intersect (t/interval start 
                                 (t/plus start (t/seconds 15)))
                     (t/interval (t/minus start (t/seconds 10))
															   (t/minus start (t/seconds 5))))
			))))

(deftest test-intersect-overlap
	(testing "intersect"
		(let [start (t/now)]
			(is (t/interval (t/plus start (t/seconds 5)) (t/plus start (t/seconds 10)))
					(intersect (t/interval (t/minus start (t/seconds 10))
															   (t/plus start (t/seconds 10)))
										 (t/interval (t/plus start (t/seconds 5))
                                 (t/plus start (t/seconds 15))))
			))))	

(deftest test-intersect-overlap-rev
	(testing "intersect"
		(let [start (t/now)]
			(is (t/interval (t/plus start (t/seconds 5)) (t/plus start (t/seconds 10)))
					(intersect (t/interval (t/plus start (t/seconds 5))
                                 (t/plus start (t/seconds 15)))
                     (t/interval (t/minus start (t/seconds 10))
															   (t/plus start (t/seconds 10))))
			))))	
