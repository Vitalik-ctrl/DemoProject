-- CREATE OR REPLACE FUNCTION remove_users_without_info()
--     RETURNS trigger AS
-- $$
-- BEGIN
--     IF NEW.age IS NULL OR NEW.name IS NULL OR NEW.email IS NULL OR NEW.gender IS NULL THEN
--         IF EXISTS (SELECT * FROM special_table WHERE id = NEW.id) THEN
--             DELETE FROM special_table WHERE id = NEW.id;
--         END IF;
--     END IF;
--     RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- CREATE TRIGGER remove_users_without_info_trigger
--     AFTER INSERT OR UPDATE OR DELETE
--     ON special_table
--     FOR EACH ROW
-- EXECUTE FUNCTION remove_users_without_info();