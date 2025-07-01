-- 코드를 작성해주세요
select YEAR(differentiation_date) as YEAR, 
(
    select max(SIZE_OF_COLONY) from ecoli_data
    where year(differentiation_date) = YEAR
) - size_of_colony as YEAR_DEV,
id
from ecoli_data
order by YEAR, YEAR_DEV
