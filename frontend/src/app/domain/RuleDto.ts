export class RuleDto {

  public id: string;
  public source: string;
  public eventType: string;
  public eventId: string;
  public category: string;
  public message: string;
  public priority: string;
  public name: string;
  public numOfLogs: number;
  public inSeconds: number;

  constructor();

  constructor(source: string,
              eventType: string,
              eventId: string,
              category: string,
              message: string,
              priority:string,
              name: string,
              numOfLogs: number,
              inSeconds: number);

  constructor(source?: string,
              eventType?: string,
              eventId?: string,
              category?: string,
              message?: string,
              priority?: string,
              name?: string,
              numOfLogs?: number,
              inSeconds?: number) {
    this.source = source;
    this.eventType = eventType;
    this.eventId = eventId;
    this.category = category;
    this.message = message;
    this.priority = priority;
    this.name = name;
    this.numOfLogs = numOfLogs;
    this.inSeconds = inSeconds;
  }

}
