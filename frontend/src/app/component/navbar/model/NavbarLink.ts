export class NavbarLink
{
  private _text: string;
  private _link: string;

  constructor();

  constructor(text: string, link: string);

  constructor(text?: string, link?: string) {
    this._text = text;
    this._link = link;
  }

  get text(): string {
    return this._text;
  }

  set text(value: string) {
    this._text = value;
  }

  get link(): string {
    return this._link;
  }

  set link(value: string) {
    this._link = value;
  }
}
