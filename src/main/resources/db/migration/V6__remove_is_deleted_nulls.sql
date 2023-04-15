update users set is_deleted = false where users.is_deleted is null;
drop function check_user_country() cascade;