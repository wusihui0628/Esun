CREATE PROCEDURE sp_disable_vote_item
  @ItemId int
AS
BEGIN
SET NOCOUNT ON;

    IF NOT EXISTS (
        SELECT Id
        FROM Votes
        WHERE ItemId = @ItemId
    )
    BEGIN
        RAISERROR('Vote item not found.', 16, 1);
        RETURN;
    END
    DELETE FROM Votes WHERE ItemId = @ItemId;
    DELETE FROM Voters WHERE VoteItemId = @ItemId;
END
