CREATE PROCEDURE sp_submit_vote
    @PersonID INT,
    @UserName VARCHAR(255),
    @VoteItemId INT,
    @VoteItemName VARCHAR(255)
AS
BEGIN
    DECLARE @Cnt INT;
    SELECT @Cnt = COUNT(*)+1 FROM Voters WHERE VoteItemId = @VoteItemId;
    INSERT INTO Voters VALUES (@PersonID, @UserName, @VoteItemId, @VoteItemName);
    UPDATE Votes SET Votes = @Cnt WHERE ItemId = @VoteItemId;
END