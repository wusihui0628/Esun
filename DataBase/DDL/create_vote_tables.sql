--  Create vote item
CREATE TABLE Votes (
  ItemId INT PRIMARY KEY,
  Items varchar(255) NOT NULL,
  Votes INT NOT NULL default 0,
);

-- Create voters (vote records)
CREATE TABLE Voters (
  PersonID int NOT NULL,
  UserName varchar(255) NOT NULL,
  VoteItemId int,
  VoteItemName varchar(255)
);