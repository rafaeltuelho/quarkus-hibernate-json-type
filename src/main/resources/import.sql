-- A 100 record random sample from https://github.com/quarkusio/quarkus-super-heroes/blob/characterdata/all-heroes.sql

INSERT INTO hero(id, name, otherName, picture, powers, level)
VALUES (
nextval('hibernate_sequence'),
  'Chewbacca', 
  '', 
  'https://raw.githubusercontent.com/quarkusio/quarkus-super-heroes/characterdata/images/chewbacca--684239239428094811.jpg', 
  '[
    {
      "name": "Ability Shift",
      "tier": "Base",
      "score": 5,
      "aliases": null,
      "description": "Ability shift is the power to use a variety of powers through natural or artificial means. The drawback is that it is usually impossible to use two powers at once."
    },
    {
      "name": "Abstract Existence",
      "tier": "Base",
      "score": 2,
      "aliases": null,
      "description": "the ability to embody an abstraction, such as a concept, thought, or an information, and being immortal thanks to it."
    },
    {
      "name": "Acausality",
      "tier": "Major",
      "score": 12,
      "aliases": null,
      "description": "The ability to act unrestrained by conventional cause and effect, on a scale that varies depending on the character. For some characters, this means not being affected by changes to the past; for others, this means defying all logic and acting with disregard for traditional causality."
    }]', 
    30);
