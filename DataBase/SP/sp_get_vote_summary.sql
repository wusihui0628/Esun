CREATE PROCEDURE sp_get_vote_summary
AS
BEGIN
    SELECT ItemId, Items, Votes FROM Votes;
END