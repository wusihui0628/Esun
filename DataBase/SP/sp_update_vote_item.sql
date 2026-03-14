CREATE PROCEDURE sp_update_vote_item
    @ItemId INT,
    @ItemName VARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (
        SELECT ItemId
        FROM Votes
        WHERE ItemId = @ItemId
    )
    BEGIN
        RAISERROR('Vote item not found.', 16, 1);
        RETURN;
    END

    UPDATE Votes
    SET Items = @ItemName
    WHERE ItemId = @ItemId;
END;