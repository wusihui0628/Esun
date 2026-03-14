CREATE PROCEDURE sp_insert_vote_item
AS
BEGIN
    DECLARE @MaxItemId INT;
    SELECT @MaxItemId=isnull(max(ItemId),0)+1 FROM Votes;
    INSERT INTO Votes (ItemId,Items,Votes) VALUES (@MaxItemId,'test',0);
END