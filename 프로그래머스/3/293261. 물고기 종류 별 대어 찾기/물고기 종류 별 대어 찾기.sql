-- 코드를 작성해주세요
select fi.ID, fn.FISH_NAME, fi.LENGTH
from fish_info as fi 
join fish_name_info as fn on fi.fish_type = fn.fish_type
where
    fi.LENGTH = (
        select max(LENGTH)
        from fish_info
        where fish_type = fi.fish_type
    );